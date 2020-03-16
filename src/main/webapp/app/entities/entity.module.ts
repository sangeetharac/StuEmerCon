import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'student',
        loadChildren: () => import('./student/student.module').then(m => m.StuemerconStudentModule)
      },
      {
        path: 'parent',
        loadChildren: () => import('./parent/parent.module').then(m => m.StuemerconParentModule)
      },
      {
        path: 'teacher',
        loadChildren: () => import('./teacher/teacher.module').then(m => m.StuemerconTeacherModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class StuemerconEntityModule {}
