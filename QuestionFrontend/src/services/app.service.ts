import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
//import {Observable} from 'rxjs/Observable';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http:HttpClient) { 
   
  }

  getTemplates(){
    return this.http.get('/server/api/templates');

  }

  getQuestions(){
    return this.http.get('/server/api/questions');
  }

  getMappings(){
    return this.http.get('/server/api/mapping');
  }

  AddQuestion(questions){
    let body = JSON.stringify(questions);
    return this.http.post('/server/api/questions', body ,httpOptions);
  }

  AddTemplate(template)
  {
    let body = JSON.stringify(template);
   
    return this.http.post('/server/api/templates', body ,httpOptions);

  }

  UpdateTemplateStatus(id,template)
  {

    return this.http.put('/server/api/templates/'+id,template,httpOptions);

  }

  AddMapping(mapping)
  {
    console.log(mapping);
    let body = JSON.stringify(mapping);
    return this.http.post('/server/api/mapping',body,httpOptions);
  }

  UpdateMapping(mapping)
  {
    let body = JSON.stringify(mapping);
    return this.http.put('/server/api/mapping',mapping,httpOptions);
  }

  DeleteMapping(mappingId)
  {
    return this.http.delete('/server/api/mapping/'+mappingId);
  }

}