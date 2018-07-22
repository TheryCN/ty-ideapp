import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'

import { Idea } from './entities/idea';

@Injectable()
export class IdeaService {

  private ideasByWorkspaceUrl = '/api/workspaces/{workspaceId}/ideas';

  constructor(private http: HttpClient) { }

  getIdeas(workspaceId:number): Observable<Idea[]> {
    return this.http.get(this.ideasByWorkspaceUrl.replace('{workspaceId}', String(workspaceId)))
      .map((data:any) => {
        return data._embedded.ideas as Idea[];
      });
  }
}
