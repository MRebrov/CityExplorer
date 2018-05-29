import {Component, OnInit} from '@angular/core';
import {TaskDTO} from "../task.model";

@Component({
  selector: 'app-schedullers',
  templateUrl: './schedullers.component.html',
  styleUrls: ['./schedullers.component.css']
})
export class SchedullersComponent implements OnInit {

  forms: TaskDTO[] = [];
  toAdd:TaskDTO = new TaskDTO();

  opts = ['Find suspicious', 'Find inactive'];

  reportSelected: boolean = false;
  loading: boolean = false;

  changeSwitch(event) {
    this.reportSelected = !this.reportSelected;
  }

  deleteFromTable(i){
    this.forms.splice(i,1);
  }

  addSpotForm() {
    if(this.reportSelected){
      this.toAdd.option = "Generate report";
    }
    else {
      this.toAdd.option = "Cleare database";
    }
    this.reportSelected = false;
    this.forms.push(this.toAdd);
    this.toAdd = new TaskDTO();
  }

  constructor() {
  }

  ngOnInit() {
  }

}
