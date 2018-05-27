import {Component, OnInit} from '@angular/core';
import {UserService} from "../user/user.service";
import {User} from "../user/user.model";
import {Router} from "@angular/router";
import {QuestService} from "../quest/quest.service";
import {QuestDTO} from "../quest/quest.model";
import {SecurityService} from "../security/security.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private securityService: SecurityService,
              private router: Router,
              private questService: QuestService,
              private userService: UserService) {
  }

  user: User = new User('', '', '', '', '', '', 0, null,0);
  topQuest: QuestDTO = new QuestDTO('', '', null, 0, 0, 0, 0);
  loading: boolean = false;
  countUsersByMonths: number[] = [];
  obj: any;
  users: User[] = [];
  userSelected: boolean = true;
  userSearchPattern: User = new User('', '', '', '', '', '', 0, null,0);
  emptyFields: boolean = true;
  load: boolean = false;

  public lineChartData: Array<any> = [{data: [], label: 'New Users'},];
  public lineChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September',
    'October', 'November', 'December'];
  public lineChartOptions: any = {
    responsive: true
  };
  public lineChartColors: Array<any> = [
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
  ];
  public lineChartLegend: boolean = true;
  public lineChartType: string = 'line';

  public randomize(): void {
    let _lineChartData: Array<any> = new Array(this.lineChartData.length);
    for (let i = 0; i < this.lineChartData.length; i++) {
      _lineChartData[i] = {data: new Array(this.lineChartData[i].data.length), label: this.lineChartData[i].label};
      for (let j = 0; j < this.lineChartData[i].data.length; j++) {
        _lineChartData[i].data[j] = Math.floor((Math.random() * 100) + 1);
      }
    }
    this.lineChartData = _lineChartData;
  }

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }

  ngOnInit() {
    this.loading = true;
    this.userService.getCurrentUser()
      .subscribe(
        (user: User) => {
          if (user.groupID.name != 'Admin') {
            this.router.navigate(['/login']);
          }

        }
      );
    this.userService.getRegistrationStatistics()
      .subscribe(
        (users: any[]) => {
          this.countUsersByMonths = users;
          this.obj = {data: this.countUsersByMonths, label: 'New users'};
          let _lineChartData: Array<any> = new Array(this.lineChartData.length);
          _lineChartData[0] = {data: new Array(users.length), label: this.lineChartData[0].label};
          _lineChartData[0].data = users;
          this.lineChartData = _lineChartData;
          console.log("from users: " + users);
          console.log("received: " + _lineChartData[0].data);
          console.log("chart:" + this.lineChartData[0].data);
        },
        (error) => {
          console.log(error)
        }
      );
    this.questService.getTopQuest()
      .subscribe(
        (topQuest: any) => {
          this.topQuest = topQuest;
        },
        (error) => {
          console.log(error);
        },
        () => {
          this.loading = false;
        }
      );


  }

  banUser(user) {
    this.users.splice(this.users.indexOf(user), 1);
    this.userService.banUser(user).catch((response: Response) => {
      return Observable.throw(response);
    }).subscribe((obj: string) => {
      window.alert(obj);
    })
  }

  changeSwitch(event) {
    this.userSelected = !this.userSelected;
  }

  checkEmpty($event) {
    // if($event.target.value == ''){
    //   if (this.date == undefined || this.name.trim() == '' || this.email.trim() == ''){
    //     this.emptyFields = true;
    //   }
    // }
    // this.emptyFields = false;
  }

  search(userSelected) {
    this.load = true;
    if (userSelected) {
      this.userService.getUsersByCriteria(this.userSearchPattern).catch((response: Response) => {
        return Observable.throw(response);
      }).subscribe((obj: any[]) => {
          this.users = obj;
        },
        (error) => {
          console.log(error);
        },
        () => this.load = false);
    }
  }

}
