import {Component, OnInit} from '@angular/core';
import {TaskDTO} from "../task.model";
import {SchedullerService} from "./scheduller.service";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-schedullers',
  templateUrl: './schedullers.component.html',
  styleUrls: ['./schedullers.component.css']
})
export class SchedullersComponent implements OnInit {

  forms: TaskDTO[] = [];
  toAdd: TaskDTO = new TaskDTO();

  opts = ['Find suspicious', 'Find inactive', 'Find banned/closed'];

  reportSelected: boolean = false;
  loading: boolean = false;

  changeSwitch(event) {
    this.reportSelected = !this.reportSelected;
  }

  deleteFromTable(i) {
    this.forms.splice(i, 1);
  }

  static validate(form) {
    console.log(form.execution);
    if (new Date(form.execution) < new Date()) {
      return 0;
    }
    return 1;
  }

  addSpotForm() {
    if (this.reportSelected) {
      this.toAdd.option = "Generate report";
    }
    else {
      this.toAdd.option = "Clear database";
    }
    this.reportSelected = false;
    if (!SchedullersComponent.validate(this.toAdd)) {
      this.toAdd.execution = null;
      window.alert("Execution date has to be in future");
    }
    else {
      this.forms.push(this.toAdd);
      this.loading = true;
      this.schedulerService.sendTask(this.toAdd).catch((response: Response) => {
        return Observable.throw(response)
      }).subscribe(
        (res: string) => {
          window.alert(res);
        },
        (error)=>console.log(error),
        ()=>this.loading = false
      );
      this.toAdd = new TaskDTO();

    }
  }

  constructor(private schedulerService: SchedullerService) {
  }

  ngOnInit() {
    this.loading = true;
    this.schedulerService.getAllTasks()
      .subscribe(
        (tasks: TaskDTO[]) => {
          this.forms = tasks;
          for(let t of tasks){
            var i=0;
            t.execution = new Date(t.execution);
            this.forms.splice(i,1,t);
          }
        },
        (error) => {
          console.log(error);
        },
        () => {
          this.loading = false;
        }
      )
  }

}
