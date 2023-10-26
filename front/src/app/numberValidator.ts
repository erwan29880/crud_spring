import {AbstractControl, ValidationErrors, ValidatorFn} from '@angular/forms';

export function numberValidator(): ValidatorFn {
    return (control:AbstractControl) : ValidationErrors | null => {

        const value = control.value;
        let numero: number|null = null;

        if (!value) {
            return null;
        }

        try{
            numero = parseInt(value);
        } catch (e) {}

        if(numero === null) {
            return null;
        }
         
        if(numero.toString().length === 10) {
            return {numero:true}
        }

        return null;        
    }
}