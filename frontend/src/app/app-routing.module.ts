import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from 'src/services/auth.guard';
import { AuthorizationComponent } from './authorization/authorization.component';
import { DdashboardComponent } from './ddashboard/ddashboard.component';

const routes: Routes = [
  {
    path:"login",
    component: AuthorizationComponent,
    pathMatch:"full"
  },
  {
    path:"dashboard",
    component: DdashboardComponent,
    pathMatch:"full",
    canActivate:[AuthGuard],
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
