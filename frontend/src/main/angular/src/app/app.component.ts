import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'Competitie Manager';

  ngOnInit(): void {
    console.log('%c nieuwsgierig?', 'background: #DC143C; color: #bada55; font-size: large');
  }
}
