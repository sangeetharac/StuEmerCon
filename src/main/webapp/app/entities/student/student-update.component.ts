import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IStudent, Student } from 'app/shared/model/student.model';
import { StudentService } from './student.service';
import { IParent } from 'app/shared/model/parent.model';
import { ParentService } from 'app/entities/parent/parent.service';
import { ITeacher } from 'app/shared/model/teacher.model';
import { TeacherService } from 'app/entities/teacher/teacher.service';

type SelectableEntity = IParent | ITeacher;

@Component({
  selector: 'jhi-student-update',
  templateUrl: './student-update.component.html'
})
export class StudentUpdateComponent implements OnInit {
  isSaving = false;
  parents: IParent[] = [];
  teachers: ITeacher[] = [];

  editForm = this.fb.group({
    id: [],
    firstName: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
    lastName: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
    grade: [null, [Validators.max(5)]],
    gender: [],
    birthDate: [],
    emr1FirstName: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
    emr1LastName: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
    emr1RelationShip: [],
    emr1Email: [null, [Validators.required, Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$')]],
    emr1PhoneNo: [],
    emr2FirstName: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
    emr2LastName: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(80)]],
    emr2RelationShip: [],
    emr2Email: [null, [Validators.required, Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$')]],
    emr2PhoneNo: [],
    parent: [],
    teacher: []
  });

  constructor(
    protected studentService: StudentService,
    protected parentService: ParentService,
    protected teacherService: TeacherService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ student }) => {
      if (!student.id) {
        const today = moment().startOf('day');
        student.birthDate = today;
      }

      this.updateForm(student);

      this.parentService
        .query({ filter: 'student-is-null' })
        .pipe(
          map((res: HttpResponse<IParent[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IParent[]) => {
          if (!student.parent || !student.parent.id) {
            this.parents = resBody;
          } else {
            this.parentService
              .find(student.parent.id)
              .pipe(
                map((subRes: HttpResponse<IParent>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IParent[]) => (this.parents = concatRes));
          }
        });

      this.teacherService.query().subscribe((res: HttpResponse<ITeacher[]>) => (this.teachers = res.body || []));
    });
  }

  updateForm(student: IStudent): void {
    this.editForm.patchValue({
      id: student.id,
      firstName: student.firstName,
      lastName: student.lastName,
      grade: student.grade,
      gender: student.gender,
      birthDate: student.birthDate ? student.birthDate.format(DATE_TIME_FORMAT) : null,
      emr1FirstName: student.emr1FirstName,
      emr1LastName: student.emr1LastName,
      emr1RelationShip: student.emr1RelationShip,
      emr1Email: student.emr1Email,
      emr1PhoneNo: student.emr1PhoneNo,
      emr2FirstName: student.emr2FirstName,
      emr2LastName: student.emr2LastName,
      emr2RelationShip: student.emr2RelationShip,
      emr2Email: student.emr2Email,
      emr2PhoneNo: student.emr2PhoneNo,
      parent: student.parent,
      teacher: student.teacher
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const student = this.createFromForm();
    if (student.id !== undefined) {
      this.subscribeToSaveResponse(this.studentService.update(student));
    } else {
      this.subscribeToSaveResponse(this.studentService.create(student));
    }
  }

  private createFromForm(): IStudent {
    return {
      ...new Student(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      grade: this.editForm.get(['grade'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      birthDate: this.editForm.get(['birthDate'])!.value ? moment(this.editForm.get(['birthDate'])!.value, DATE_TIME_FORMAT) : undefined,
      emr1FirstName: this.editForm.get(['emr1FirstName'])!.value,
      emr1LastName: this.editForm.get(['emr1LastName'])!.value,
      emr1RelationShip: this.editForm.get(['emr1RelationShip'])!.value,
      emr1Email: this.editForm.get(['emr1Email'])!.value,
      emr1PhoneNo: this.editForm.get(['emr1PhoneNo'])!.value,
      emr2FirstName: this.editForm.get(['emr2FirstName'])!.value,
      emr2LastName: this.editForm.get(['emr2LastName'])!.value,
      emr2RelationShip: this.editForm.get(['emr2RelationShip'])!.value,
      emr2Email: this.editForm.get(['emr2Email'])!.value,
      emr2PhoneNo: this.editForm.get(['emr2PhoneNo'])!.value,
      parent: this.editForm.get(['parent'])!.value,
      teacher: this.editForm.get(['teacher'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStudent>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
