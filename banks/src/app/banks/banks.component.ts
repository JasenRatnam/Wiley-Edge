import { Component, OnInit } from '@angular/core';
import { Bank } from '../bank';
import { BANKS } from '../banks';

@Component({
  selector: 'app-banks',
  templateUrl: './banks.component.html',
  styleUrls: ['./banks.component.css']
})
export class BanksComponent implements OnInit {
  banks = BANKS;
  selectedBank: Bank | undefined;

  constructor() { }

  onSelect(bank: Bank): void {
    this.selectedBank = bank;
  }

  ngOnInit(): void {
  }

}
