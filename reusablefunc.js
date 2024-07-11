
export const runLoginTest = (usernameSelector, sendOtpButtonSelector, otpInputSelector, verifyOtpButtonSelector , username, otp) => {
    cy.wait(2000);
    // Locators and data for login
    // const usernameSelector = "#ion-input-4";
    // const sendOtpButtonSelector = 'ion-button:contains("send otp")';
    // const otpInputSelector = "#ion-input-5"; // Adjust the selector based on the actual OTP input field
    // const verifyOtpButtonSelector = 'ion-button:contains(" OTP Verification")'; // Adjust the selector based on the actual OTP verification button

    // const username = "9021708264";
    // const otp = "12345"; // Replace with the actual OTP or a mechanism to fetch it

    
    // Step 1: Click on the LOGIN button
    cy.contains("strong", "Click here to LOGIN").click();

    // Step 2: Verify the new URL and visit the OTP page
    cy.url().should("include", "/otp");

    // Enter mobile number
    cy.get(usernameSelector).type(username);

    // Click on the SEND OTP button
    cy.get(sendOtpButtonSelector).click();

    let isSuccess = false;

    Cypress.config('defaultCommandTimeout', 10000);

    // Handle alerts after sending OTP
    cy.get('.alert-head').then($alertHead => {
      if ($alertHead.find('[id^="alert-"][id$="-hdr"]').length) {
        const successAlertHdr = $alertHead.find('[id^="alert-"][id$="-hdr"]:contains("Success")');
        if (successAlertHdr.length) {
          // Handle success alert
          cy.get('[id^="alert-"][id$="-hdr"]:contains("Success")')
            .should('contain.text', 'Success');
          cy.get('[id^="alert-"][id$="-msg"]').then($alertMsg => {
            const alertMessage = $alertMsg.text();
            cy.log(alertMessage);             
             isSuccess = true;
          });
        } else {
          const errorAlertHdr = $alertHead.find('[id^="alert-"][id$="-hdr"]:contains("Error")');
          if (errorAlertHdr.length) {
            // Handle error alert
            cy.get('[id^="alert-"][id$="-hdr"]:contains("Error")')
              .should('contain.text', 'Error');
            cy.get('[id^="alert-"][id$="-sub-hdr"]').then($alertSubHdr => {
              const alertMessage = $alertSubHdr.text();
              cy.log(alertMessage);
            });
          } else {
            cy.log('No alert found');
          }
        }
      } else {
        cy.log('No alert found');
      }
    }).then(() => {
      // Conditional logic based on isSuccess flag
      if (isSuccess) {
        cy.get(otpInputSelector).type(otp);
        cy.get(verifyOtpButtonSelector).click();

        // Handle alerts after OTP verification
          cy.get('.alert-head').then($alertHead => {
          const alertHdr = $alertHead.find('[id^="alert-"][id$="-hdr"]');
          if (alertHdr.length) {
            if (alertHdr.text().includes("Success")) {
              cy.log("Success");
              const alertMsg = $alertHead.find('[id^="alert-"][id$="-msg"]').text();
              cy.log(alertMsg);

              // Click on menu option
            //  cy.get('.buttons-first-slot > .md').click();
            //  handleUserMenuChoice();

            } 
            else if (alertHdr.text().includes("Error")) {
              cy.log("Error");
              const alertSubHdr = $alertHead.find('[id^="alert-"][id$="-sub-hdr"]').text();
              cy.log(alertSubHdr);
            } else {
              cy.log('No alert found after OTP verification');
            }
          } else {
            cy.log('No alert found after OTP verification');
          }
        });
      } else {
        cy.log("Not a registered number");
      }
      
    });
    
  }

  export const goToMyConnection = () => {
    cy.get('.buttons-first-slot > .md').click();
    cy.get('ion-menu-toggle.md > [routerlink="/my-connection"]').click();
    cy.wait(2000);
  }

  export const receivedRequest = () =>
  {
    
    cy.wait(2000);
    cy.get("body").then(($body) => {
        if ($body.find("app-user ion-card.profile-card").length > 0) {
          cy.log("card is present");
          cy.get('ion-card ion-card-header ion-card-title').then(($titles) => {
            // Get the count of ion-card-title tags
            let count = 0;
            count = $titles.length;                       
            cy.log(`Received Request Count:${count}`);
                          
    })         
        } else {
          cy.log("card is not present");
        } 
    })
    
}

export const sentRequest = () =>
{
 
 cy.get(':nth-child(2) > .sc-ion-label-md-h').click({force:true});
 cy.wait(2000);

 cy.get("body").then(($body) => {
  if ($body.find("app-user ion-card.profile-card").length > 0) {
    cy.log("card is present");
    cy.get('ion-card ion-card-header ion-card-title').then(($titles) => 
      {
      // Get the count of ion-card-title tags
      let count = 0;
      count = $titles.length;                       
      cy.log(`Sent Request Count:${count}`);
                    
})         
  } else {
    cy.log("card is not present");
  } 
})
  }

  export const myConnections = () =>
  {
    // let isPresent = false;
    cy.get(':nth-child(3) > .sc-ion-label-md-h').click({force:true});
    
    cy.get("body").then(($body) => 
    {
        if ($body.find("app-user ion-card.profile-card").length > 0) 
        {
          cy.log("card is present");
          cy.get('ion-card ion-card-header ion-card-title').then(($titles) => 
            {
            // Get the count of ion-card-title tags
            let count = 0;
            count = $titles.length;                       
            cy.log(`My Connections:${count}`);
            })                   
        } else {
          cy.log("No Connections!");
        } 
  
    });
  }

 export const logout = () =>
  {
    cy.get('ion-menu-toggle.md > :nth-child(4)').click();
    cy.get('button > span').contains('Logout').click();
  }