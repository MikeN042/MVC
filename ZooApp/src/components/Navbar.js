const Navbar = () => {
    return (  

        <nav className="navbar">
            <h1>Woderland Zoo</h1>
            <a href="/">Home</a>
            <a href="/create" style={{
                color:"white",
                backgroundColor:"#f1256d",
                borderRadius:"8px"
            }}>New Animal </a>
         </nav>
    );
}
 
export default Navbar;