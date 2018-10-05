import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { GrowlModule } from 'primeng/primeng';
import { ButtonModule } from 'primeng/primeng';

import { IdeaService } from './idea.service';
import { MessageService } from 'primeng/components/common/messageservice';

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
    HttpClientModule,
    GrowlModule,
    ButtonModule,
    BrowserAnimationsModule

  ],
  providers: [IdeaService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
