describe('App Title', () => {
  it('renders the app title', () => {
    cy.visit('http://localhost:3000/')
    cy.get('[data-testid="cypress-title"]').should('exist')
    .should('have.text','Wonderland Zoo')
  })

  it('renders the navbar component', () => {
    cy.visit('http://localhost:3000/')
    cy.get('[data-testid="navbar"]').should('exist')
  })

  it('renders the Home component', () => {
    cy.visit('http://localhost:3000/')
    cy.get('[data-testid="home"]').should('exist')
  })

  it('renders the navbar', () => {
    cy.visit('http://localhost:3000/')
    cy.get('[data-testid="navbar"]').should('exist')
  })

  it('renders the navbar', () => {
    cy.visit('http://localhost:3000/')
    cy.get('[data-testid="navbar"]').should('exist')
  })
  
})