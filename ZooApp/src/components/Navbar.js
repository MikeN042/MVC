import {Link} from 'react-router-dom';

const Navbar = () => {
    return (  
        <nav className="navbar" data-testid='navbar'>
            <h1 data-testid='cypress-title'>Wonderland Zoo</h1>
            <div className='links'>
                <Link data-testid='navbar-home-bt' to="/">Home</Link>
                <Link data-testid='navbar-create-bt' to="/create/animal">New Animal</Link>
            </div>
         </nav>
    );
}
 
export default Navbar;