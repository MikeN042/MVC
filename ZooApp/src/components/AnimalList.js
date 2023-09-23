const AnimalList = ({animals,keeper,handleDelete}) => {
    

    return ( 
        <div className='animal-list'>
            <h2>{keeper}</h2>
            {animals.map((animal) => (
                <div className = 'animal-preview' key={animal.id}>
                    <h2>{animal.name}</h2>
                    <p>Enclolsure: {animal.enclosure}, Temperment:
                        <span style={animal.temperment === 'Agressive' ? {"fontWeight":"bold","color":"#f1356d"}:{} }> 
                        {` ${animal.temperment}`}
                        </span> </p>
                    <p>Last Fed: {animal.lastFeedingTime}</p> 
                    <button onClick={()=>handleDelete(animal.id)}>delete animal</button>
                </div>
            ))}

        </div>    
     );
}
 
export default AnimalList;