import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StuemerconSharedModule } from 'app/shared/shared.module';
import { ParentComponent } from './parent.component';
import { ParentDetailComponent } from './parent-detail.component';
import { ParentUpdateComponent } from './parent-update.component';
import { ParentDeleteDialogComponent } from './parent-delete-dialog.component';
import { parentRoute } from './parent.route';

@NgModule({
  imports: [StuemerconSharedModule, RouterModule.forChild(parentRoute)],
  declarations: [ParentComponent, ParentDetailComponent, ParentUpdateComponent, ParentDeleteDialogComponent],
  entryComponents: [ParentDeleteDialogComponent]
})
export class StuemerconParentModule {}
