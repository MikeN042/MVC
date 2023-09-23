import { useState, useEffect } from "react"
import AnimalList from "./AnimalList";

const Home = () => {

    const [animals,setAnimals] = useState([
        {name:'Simba',keeper:'Bilbo Baggins',enclosure:'S-11', temperment:'Playful', age:'10',lastFeedingTime:'09/08/2014, 5:35:56 PM',id:1},
        {name:'Mufasa',keeper:'Bilbo Baggins',enclosure:'S-12', age:'30',temperment:'Cautious',lastFeedingTime:'09/08/2014, 5:35:56 PM',id:2},
        {name:'Scar',keeper:'Frodo Baggins',enclosure:'S-10', age:'',temperment:'Agressive',lastFeedingTime:'09/08/2014,5:35:56 PM',id:3},
        {name:'Timone',keeper:'Frodo Baggins',enclosure:'S-21', age:'20',temperment:'Playful',lastFeedingTime:'09/08/2014, 5:35:56 PM',id:4}
    ])

    const handleDelete = (id) => {
        const newAnimals = animals.filter(animal=>animal.id !== id)
        setAnimals(newAnimals)
    }



    return ( 
        <div className="home">
            <AnimalList animals={animals.filter((animal)=>animal.keeper === 'Bilbo Baggins')} keeper='Bilbo' handleDelete={handleDelete}/>
            <AnimalList animals={animals.filter((animal)=>animal.keeper === 'Frodo Baggins')} keeper = 'Frodo' handleDelete={handleDelete}/>
        </div>

     );
}
export default Home;