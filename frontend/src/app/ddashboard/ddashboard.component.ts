import { Component, OnInit } from '@angular/core';
import { PensionDisbursementService } from 'src/services/pension-disbursement.service';

@Component({
  selector: 'app-ddashboard',
  templateUrl: './ddashboard.component.html',
  styleUrls: ['./ddashboard.component.css']
})
export class DdashboardComponent implements OnInit {
  bankName = ''
  bankServiceCharge :any

  ProcessPensionInput={
    aadhaarNumber :'', 
    pensionAmount:1.0,
    bankServiceCharge:1.0
  }
  ProcessCode : any
  constructor(private pension_disbursement:PensionDisbursementService) { }

  ngOnInit(): void {
  }
  onProcessPension(){
    console.log("ProcessPension",this.ProcessPensionInput);
    this.pension_disbursement.processPension(this.ProcessPensionInput).subscribe(
      processCode=>{console.log(processCode);
        this.ProcessCode=processCode;
      },
      error=>{console.log(error);
      }
    );
  }
  ongetBankServiceCharge(){
    console.log("BankServiceCharge",this.bankName);
    this.pension_disbursement.getBankServiceCharge(this.bankName).subscribe(
      bankServiceCharge=>{console.log(bankServiceCharge);
        this.bankServiceCharge=bankServiceCharge;
      },
      error=>{console.log(error);
      }
    );
  }

}
