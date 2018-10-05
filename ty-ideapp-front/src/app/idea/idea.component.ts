import { Component, OnInit, Input } from '@angular/core';

import { Idea } from '../entities/idea';

@Component({
  selector: 'app-idea',
  templateUrl: './idea.component.html',
  styleUrls: ['./idea.component.css']
})
export class IdeaComponent implements OnInit {

  @Input() idea:Idea;

  ratingArray:number[];

  editable:boolean = false;

  constructor() { }

  ngOnInit() {
    this.ratingArray = Array(this.idea.rating).fill().map((x,i)=>i);
  }

}
