import { Component, OnInit } from '@angular/core';
import {QuestService} from "../quest.service";
import {QuestDTO} from "../quest.model";

@Component({
  selector: 'app-quest-list',
  templateUrl: './quest-list.component.html',
  styleUrls: ['./quest-list.component.css']
})
export class QuestListComponent implements OnInit {

  quests: QuestDTO[] = [];

  constructor(private questService: QuestService) { }

  ngOnInit() {
    this.questService.getQuests()
      .subscribe((list: any[])=>{ //TODO: TRANSLATE RESPONSE INTO DTO
      console.log(list.toString());
      this.quests = list});
  }




}
