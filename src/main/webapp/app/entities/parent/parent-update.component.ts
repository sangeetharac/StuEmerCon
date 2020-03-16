import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IParent, Parent } from 'app/shared/model/parent.model';
import { ParentService } from './parent.service';

@Component({
  selector: 'jhi-parent-update',
  templateUrl: './parent-update.component.html'
})
export class ParentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    firstName: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
    lastName: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
    parentOrGuardien: [],
    email: [null, [Validators.required, Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$')]],
    phoneNo: [],
    addressLine1: [null, [Validators.minLength(3), Validators.maxLength(80)]],
    addressLine2: [null, [Validators.minLength(3), Validators.maxLength(80)]],
    zipcode: []
  });

  constructor(protected parentService: ParentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ parent }) => {
      this.updateForm(parent);
    });
  }

  updateForm(parent: IParent): void {
    this.editForm.patchValue({
      id: parent.id,
      firstName: parent.firstName,
      lastName: parent.lastName,
      parentOrGuardien: parent.parentOrGuardien,
      email: parent.email,
      phoneNo: parent.phoneNo,
      addressLine1: parent.addressLine1,
      addressLine2: parent.addressLine2,
      zipcode: parent.zipcode
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const parent = this.createFromForm();
    if (parent.id !== undefined) {
      this.subscribeToSaveResponse(this.parentService.update(parent));
    } else {
      this.subscribeToSaveResponse(this.parentService.create(parent));
    }
  }

  private createFromForm(): IParent {
    return {
      ...new Parent(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      parentOrGuardien: this.editForm.get(['parentOrGuardien'])!.value,
      email: this.editForm.get(['email'])!.value,
      phoneNo: this.editForm.get(['phoneNo'])!.value,
      addressLine1: this.editForm.get(['addressLine1'])!.value,
      addressLine2: this.editForm.get(['addressLine2'])!.value,
      zipcode: this.editForm.get(['zipcode'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IParent>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
