import { Component, OnInit } from '@angular/core';

import { IdeaService } from '../idea.service';

import { Idea } from '../entities/idea';

@Component({
  selector: 'app-ideas',
  templateUrl: './ideas.component.html',
  styleUrls: ['./ideas.component.css']
})
export class IdeasComponent implements OnInit {

  ideas:Idea[];

  constructor(private ideaService:IdeaService) { }

  ngOnInit() {
    this.initIdeas();
  }

  initIdeas() {
    this.ideaService.getIdeas(1).subscribe(ideas => this.ideas = ideas);
  }

}
