import { Component, Input, OnInit } from '@angular/core';
import Stock from 'src/app/shared/models/stock-model';
import { DashboardService } from '../dashboard.service';

@Component({
  selector: 'app-stock-card',
  templateUrl: './stock-card.component.html',
  styleUrls: ['./stock-card.component.css'],
})
export class StockCardComponent implements OnInit {
  @Input()
  stock: Stock = new Stock();
  constructor() {}

  ngOnInit(): void {}

  get variation(): string {
    const localizedVariationString = this.stock.variation.toLocaleString();
    const prefix = this.stock.variation < 0 ? '-' : '+';
    return `${prefix} ${localizedVariationString.replace('-', '')}%`;
  }
}
