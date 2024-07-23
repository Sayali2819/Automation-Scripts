describe('Data Driven Testing', () => {

    it('Test', () => {
        cy.fixture("orangehrm2").then((data) => {

            cy.visit("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");


            data.forEach((userdata) => {
                cy.get('input[name="username"]').type(userdata.username);
                cy.get('input[name="password"]').type(userdata.password);
                cy.get('button[type="submit"]').click();

                cy.url().then((url) => {
                    if (url.includes('/dashboard')) {
                      cy.log('Successful login');
                      
                      cy.get('.oxd-userdropdown-tab > .oxd-icon').click();
                      cy.get('a[href="/web/index.php/auth/logout"]').click();
                      cy.wait(2000);

                    } else {
                //       cy.log('Invalid Credentials');
                    cy.get('p.oxd-alert-content-text').then(($p) => {
                        if ($p.is(':visible')) {
                        const errorMessage = $p.text();
                        cy.log('Error Message: ', errorMessage);
                        } 

                        });
                    }
                
            });
            cy.wait(1000);
        })
    })
    })
})