import { Directive, ElementRef} from '@angular/core';

@Directive({
  //applies the logic of this directive
  //each element in the template that has an attribute named appHighlight 
  selector: '[appHighlight]'
})
export class HighlightDirective {

  constructor(el: ElementRef) { 
    el.nativeElement.style.color = 'black';
    el.nativeElement.style.backgroundColor = 'yellow';
  }

}
