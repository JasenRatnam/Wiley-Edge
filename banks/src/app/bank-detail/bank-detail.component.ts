import { Component, Input, OnInit } from '@angular/core';

import { Bank } from '../bank';

@Component({
  selector: 'app-bank-detail',
  templateUrl: './bank-detail.component.html',
  styleUrls: ['./bank-detail.component.css']
})
export class BankDetailComponent implements OnInit {

  @Input() bank: Bank | undefined;
  
  constructor() { }

  ngOnInit(): void {
  }

}
