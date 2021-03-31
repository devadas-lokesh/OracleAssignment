import { Observable } from 'rxjs';
import { AppService } from './../services/app.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnChanges, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';




@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'QuestionFrontend';
  typeControl = new FormControl('', Validators.required);
  newMapQuestionId:any;
  newtemplateid : any;
  QuestionCount: any=0;
  Editflag : any = false;
  newQuestion : any ;
  role: any = "user";
  newType : any;
  newOrder : any;
  editMaptemp:any;
  typeplaceholder : any="";
  questionplaceholder : any="";
  Count : any = 0;
  newQuestionVisible:any;
  tempMapping = new class{mapid:any; questionid : any; templateid : any; type: any; orderid:any;};
  
  validmessage : any;
  public question ;
  public templates; 
  public questions ; 
  public mappings ;
  public selectedTemplate;
  questionform : FormGroup;
  types = ['text box','check box','toggle','radio button'];
  


  constructor(private http:HttpClient, private appService:AppService)
  {

  }
 
  ngOnInit() 
  { 
    this.validmessage="";
    this.newQuestionVisible=false;

    this.questionform = new FormGroup({
      newQuestion: new FormControl('',Validators.required),
      newtype: new FormControl('',Validators.required),
      
    });
    console.log("init");

    this.ChangeState();
    console.log(this.templates);
   
    

    
  }
  

  ActivateTemplate(template)
  {
    
    this.appService.UpdateTemplateStatus(this.selectedTemplate.templateid,template).subscribe(
      data=>{ console.log(data);},
      error=>{console.log(error);}
    );
    this.Count=0;
    console.log(this.Count);

    this.selectedTemplate = template;
    if(this.newQuestionVisible)
    {
      this.newQuestionVisible=!this.newQuestionVisible;
    }

    this.mappings.forEach(element => {
      if(element.templateid==this.selectedTemplate.templateid)
      {
        this.Count++;
      }
    });
    console.log(this.Count);
    
    


  }

  addQuestion(mapeditflag)
  {
    if(this.questionform.valid)
    {
      console.log(this.questionform.value.newQuestion);
      
      this.question = this.questionform.value.newQuestion;
      

      this.appService.AddQuestion(this.question).subscribe(
        data=>{ console.log(data); 
          if(mapeditflag)
          {
            console.log("edit");
            this.EditTemplateMappingData(data,this.questionform.value.newtype);
            this.ChangeState();
            
          }
          else{
            console.log("insert");
            this.Count++;
          this.UpdateTempMappingData(data,this.questionform.value.newtype);
          this.ChangeState();
          
        } },
        error=>{console.log(error);}
      );
      // this.mappings=this.appService.getMappings();
      // this.questions=this.appService.getQuestions();
      // this.templates=this.appService.getTemplates();

    }
   else
   {

    this.validmessage = "Fill out all the fields."
      
    }
   
      

  }

  EditTemplateMappingData(tempMap,temptype)
  {
    this.editMaptemp.questionid = tempMap.questionId;
    this.editMaptemp.type = temptype;

    this.appService.UpdateMapping(this.editMaptemp).subscribe(
      data=>{console.log(data); },
      error=>{console.log(error);}
    );
    this.ShowQuestionWindow();
    this.ChangeState();


  }

  UpdateTempMappingData(tempMap,temptype)
  {
    
    this.newMapQuestionId=tempMap.questionId;
    this.tempMapping.mapid=0;
    this.tempMapping.questionid=tempMap.questionId;
    this.tempMapping.type = temptype;
    this.tempMapping.templateid=this.selectedTemplate.templateid;
    this.tempMapping.orderid=1;
    this.appService.AddMapping(this.tempMapping).subscribe(
      data=>{console.log(data);},
      error=>{console.log(error);}

    );

    console.log(this.tempMapping);
    this.ShowQuestionWindow();
    this.ChangeState();

    

  }

  CloneTemplate(template)
  {

    this.appService.AddTemplate(template).subscribe(
      
      data=>{ this.newtemplateid=data; this.ChangeState();  console.log(data);},
      error=>{console.log(error);}
      
    );
    
    this.ChangeState();
    

  }

  

  ReorderQuestion()
  {

  }
  UpdateTemplateData(tempdata)
  {
    this.templates=tempdata;
   
  }

  ChangeState()
  {
    this.appService.getTemplates().subscribe(
      data=>{this.templates=data; 
        this.templates.forEach(element => {

          if(element.templateactive)
          {
            this.selectedTemplate = element;
          }
          
        });
        this.UpdateTemplateData(data);
        
      },
      err => console.error(err)
      
    
    );
    

    this.appService.getQuestions().subscribe(
      data=>{this.questions=data;console.log("questions called"+this.questions);
      
      },
      err => console.error(err)
    
    );

    this.appService.getMappings().subscribe(
      data=>{this.mappings=data;},
      err => console.error(err)
    
    );
  }

  DeleteQuestions(map)
  {
    
    console.log(map);
    this.appService.DeleteMapping(map.mapid).subscribe(
      data=>{console.log("deleted");},
      error=>{console.log(error);}
    );
    this.Count--;
    this.ChangeState();
  }
  
  ShowQuestionWindow()
  {
    this.Editflag=false;
    this.editMaptemp=null;
    this.newQuestionVisible=!this.newQuestionVisible;
    this.validmessage="";
    this.typeplaceholder="";
    this.questionplaceholder="";
  }
  EditQuestionWindow(map,question)
  {
    this.typeplaceholder=map.type;
    this.questionplaceholder=question.questions;
    this.newQuestionVisible=!this.newQuestionVisible;
    this.Editflag=true;
    this.editMaptemp=map;
    console.log(this.typeplaceholder)

  }

  drop(event: CdkDragDrop<object[]>) {
    moveItemInArray(this.templates, event.previousIndex, event.currentIndex);
  }
  


}
