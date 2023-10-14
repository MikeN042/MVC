describe('App Title', () => {
    it('renders the home button',() => {
        cy.visit('http://localhost:3000/')
        cy.get('[data-testid="navbar-home-bt"]').should('exist')
        .should('have.text','Home')
    })

    it('renders the create button',() => {
        cy.visit('http://localhost:3000/')
        cy.get('[data-testid="navbar-create-bt"]').should('exist')
        .should('have.text','New Animal')
    })
})