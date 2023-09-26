import {Link} from 'react-router-dom';

const Navbar = () => {
    return (  
        <nav className="navbar">
            <h1>Wonderland Zoo</h1>
            <div className='links'>
                <Link to="/">Home</Link>
                <Link to="/create/animal" 
                >New Animal </Link>
            </div>
         </nav>
    );
}
 
export default Navbar;