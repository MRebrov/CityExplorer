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

  public spotConfirmations: SpotConfirmationDTO[];
  errorMsg: string;

  constructor(public questService: QuestService, private router: Router) {
  }

  ngOnInit() {
    this.questService.getConfirmationsList()
      .subscribe(
        (confirmations: any[]) => {
          this.spotConfirmations = confirmations;
          for (let confirmation of this.spotConfirmations) {
            confirmation.uploadDate = new Date(confirmation.uploadDate);
          }
        },
        (error) => {
          console.log(error);
          this.router.navigate(['/map']);
        });
  }

  doConfirmOrReject(spotConfirmationDTO: SpotConfirmationDTO, confirm: boolean) {
    this.questService.confirmationRequest(spotConfirmationDTO.userSpotProgressId, confirm).catch((response: Response) => {
      this.writeMsg(response.text()); //если ошибка, вывести её
      return Observable.throw(response);
    }).subscribe(
      (response: any) => {
        this.writeMsg(confirm ? 'Confirmation' : 'Rejecton' + 'completed');
        let index: number = this.spotConfirmations.indexOf(spotConfirmationDTO);
        this.spotConfirmations.splice(index, 1);
      });
  }

  writeMsg(error) {
    document.getElementById('collapseMessage').classList.add('show');
    this.errorMsg = error;
  }

}
