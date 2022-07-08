import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-services-home',
  templateUrl: './services-home.component.html',
  styleUrls: ['./services-home.component.css']
})
export class ServicesHomeComponent implements OnInit {

  bankingServices: any[] = 
  [
    {
      "type": 'Issue business loans',
      "years": 2,
      "category": "loans"
    },
    {
      "type": 'Provide periodic reports and analysis',
      "years": 7,
      "category": "reports"
    },
    {
      "type": 'Open new checking and/or saving accounts',
      "years": 5,
      "category": "financial_accounts"
    },
    {
      "type": 'Guide clients through their savings, loan, mortgage, and retirement options',
      "years": 6,
      "category":"options"
    }
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
