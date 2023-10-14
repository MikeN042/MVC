describe('App component', () => {

    it('renders create animal component',() => {
        cy.visit('http://localhost:3000/')
        cy.get('[data-testid="navbar-create-bt"]').click()
        cy.get('[data-testid="create-animal"]').should('exist')
    
    })
    
    it('accepts/requires all inputs; then renders home page on save',() => {
        cy.visit('http://localhost:3000/')
        cy.get('[data-testid="navbar-create-bt"]').click()
        cy.get('[data-testid="create-animal"]').should('exist')
        cy.get('[data-testid="create-animal-save"]').click()
        cy.get('[data-testid="create-animal"]').should('exist')
        cy.get('[data-testid="create-animal-input-name"]').type('Test Animal').should('have.value','Test Animal')
        cy.get('[data-testid="create-animal-save"]').click()
        cy.get('[data-testid="create-animal"]').should('exist')
        cy.get('[data-testid="create-animal-input-species"]').type('Test Species').should('have.value','Test Species')
        cy.get('[data-testid="create-animal-save"]').click()
        cy.get('[data-testid="create-animal"]').should('exist')
        cy.get('[data-testid="create-animal-input-birthdate"]').type('2019-10-15').should('have.value','2019-10-15')
        cy.get('[data-testid="create-animal-save"]').click()
        cy.get('[data-testid="create-animal"]').should('exist')
        cy.get('[data-testid="create-animal-input-temperament"]').select('Even').should('have.value','Even')
        cy.get('[data-testid="create-animal-save"]').click()
        cy.get('[data-testid="create-animal"]').should('exist')
        cy.get('[data-testid="create-animal-input-enclosure"]').type('Test Enclosure').should('have.value','Test Enclosure')
        cy.get('[data-testid="create-animal-save"]').click()
        cy.get('[data-testid="create-animal"]').should('exist')
        cy.get('[data-testid="create-animal-input-enclosure"]').clear()
        cy.get('[data-testid="create-animal-input-keeper"]').select('Bilbo Baggins - Senior Zookeeper').should('have.value',1)
        cy.get('[data-testid="create-animal-save"]').click()
        cy.get('[data-testid="create-animal"]').should('exist')
        cy.get('[data-testid="create-animal-input-enclosure"]').type('Test Enclosure').should('have.value','Test Enclosure')
        cy.get('[data-testid="create-animal-save"]').click()
        cy.get('[data-testid="home"]').should('exist') 
    })
    

    
    
})