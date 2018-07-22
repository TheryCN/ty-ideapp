import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { IdeaService } from './idea.service';

import { AppComponent } from './app.component';
import { IdeasComponent } from './ideas/ideas.component';
import { IdeaComponent } from './idea/idea.component';


@NgModule({
  declarations: [
    AppComponent,
    IdeasComponent,
    IdeaComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [IdeaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
