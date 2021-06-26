import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthorizationComponent } from './authorization/authorization.component';
import { DdashboardComponent } from './ddashboard/ddashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AuthGuard } from 'src/services/auth.guard';
import { AuthorizationService } from 'src/services/authorization.service';
import { AuthInterceptor } from 'src/services/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    AuthorizationComponent,
    DdashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatCardModule,
    HttpClientModule
  ],
  providers: [AuthGuard,AuthorizationService, [{provide:HTTP_INTERCEPTORS, useClass:AuthInterceptor, multi:true}] ],
  bootstrap: [AppComponent]
})
export class AppModule { }
