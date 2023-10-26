import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'numb'
})
export class NumbPipe implements PipeTransform {

  transform(value: number): string {
    let val1: string = value.toString();

    if (val1.length !== 10) return val1;

    val1 = val1.slice(0,2) + '-' + val1.slice(2,4) + '-' + val1.slice(4,6) + '-' + val1.slice(6,8) + '-' + val1.slice(8, 10); 

    return val1;
  }

}
