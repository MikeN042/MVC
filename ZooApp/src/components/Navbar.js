import {Link} from 'react-router-dom';

const Navbar = () => {
    return (  
        <nav className="navbar">
            <h1>Wonderland Zoo</h1>
            <div className='links'>
                <Link to="/">Home</Link>
                <Link to="/create/animal" 
               // style={{
               //     color:"white",
                //    backgroundColor:"#f1256d",
                //    borderRadius:"8px"
               // }}
                >New Animal </Link>
            </div>
         </nav>
    );
}
 
export default Navbar;