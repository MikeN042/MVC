import { useState, useEffect } from "react"
import AnimalList from "./AnimalList";
import useFetch from "../hooks/useFetch";
const Home = () => {


const {data: animals,isLoading,error} = useFetch('http://localhost:8080/animal')

    return ( 
        <div className="home">
            {error &&  <div>{error}</div>}
            {isLoading && <div>Loading...</div>}
            {animals && <AnimalList animals={animals} keeper='Bilbo'/>}
        </div>

     );
}
export default Home;