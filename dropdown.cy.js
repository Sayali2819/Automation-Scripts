describe('handle drop down', () => {

    it.skip('dropdown with select', () => {
        cy.visit('https://www.zoho.com/commerce/free-demo.html');
        cy.get('#zcf_address_country').select('Indonesia').should('have.value', 'Indonesia');
    });

    it('dropdown without select', () => {
        cy.visit("https://www.dummyticket.com/dummy-ticket-for-visa-application/");
        cy.get('#select2-billing_country-container').click();
        cy.get('.select2-search__field').type('Iceland').type('{enter}');
        cy.get('#select2-billing_country-container').should('have.text', 'Iceland');
    });

    it('autosuggested drop down', () => {
        cy.visit('https://www.wikipedia.org/');
        cy.get('#searchInput').type('Delhi');
        cy.get('.suggestion-title').contains('Delhi University').click();
    }) 
    
});
