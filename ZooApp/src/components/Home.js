import { useState, useEffect } from "react"
import AnimalList from "./AnimalList";

const Home = () => {

    const [animals,setAnimals] = useState([])

    const handleDelete = (id) => {
        const newAnimals = animals.filter(animal=>animal.id !== id)
        setAnimals(newAnimals)
    }

    useEffect(()=>{
        fetch('http://localhost:8080/animal')
        .then(res =>{
            return res.json()
        })
        .then(data=>{
            console.log(data)
            setAnimals(data)
        })
    },[])

    return ( 
        <div className="home">
            <AnimalList animals={animals} keeper='Bilbo' handleDelete={handleDelete}/>
        </div>

     );
}
export default Home;