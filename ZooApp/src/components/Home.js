import { useState } from "react"

const Home = () => {
    //State
    const [name,setName] = useState('mario')

    //EventsHandlers
    const handleClick = () => console.log("Hello World")

    return ( 
        <div className="home">
            <h2>Homepage</h2>
            <button onClick={handleClick}>CLick Me</button>
            <p>{name}</p>
            <button onClick={() => setName('luigi')}>CLick Me Again</button>

        </div>

     );
}
 
export default Home;