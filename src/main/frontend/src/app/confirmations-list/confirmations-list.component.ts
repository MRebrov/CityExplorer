import {Component, OnInit} from '@angular/core';
import {QuestService} from '../quest/quest.service';
import {SpotConfirmationDTO} from './spot-confirmation.model';
import {Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {Response} from '@angular/http';

@Component({
  selector: 'app-confirmations-list',
  templateUrl: './confirmations-list.component.html',
  styleUrls: ['./confirmations-list.component.css']
})
export class ConfirmationsListComponent implements OnInit {

  errorMsg: string;

  constructor(public questService: QuestService, private router: Router) {
  }

  ngOnInit() {
    if(this.questService.getLoadedConfirmations()==null) {
      this.questService.getConfirmationsList();
    }
  }

  doConfirmOrReject(spotConfirmationDTO: SpotConfirmationDTO, confirm: boolean) {
    this.questService.confirmationRequest(spotConfirmationDTO.userSpotProgressId, confirm).catch((response: Response) => {
      this.writeMsg(response.text()); //если ошибка, вывести её
      return Observable.throw(response);
    }).subscribe(
      (response: any) => {
        this.writeMsg((confirm ? 'Confirmation' : 'Rejecton') + 'completed');
        let index: number = this.questService.getLoadedConfirmations().indexOf(spotConfirmationDTO);
        this.questService.getLoadedConfirmations().splice(index, 1);
        this.questService._confirmationsCountSubject.next(this.questService.getLoadedConfirmations().length);
      });
  }

  writeMsg(error) {
    document.getElementById('collapseMessage').classList.add('show');
    this.errorMsg = error;
  }

}
