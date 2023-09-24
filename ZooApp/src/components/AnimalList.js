const AnimalList = ({animals,keeper,handleDelete}) => {
    

    return ( 
        <div className='animal-list'>
            <h2>{keeper}</h2>
            {animals.map((animal) => (
                <div className = 'animal-preview' key={animal.id}>
                    <h2>{animal.name}</h2>
                    <p>Enclolsure: {animal.enclosure}, Temperament:
                        <span style={animal.temperament === 'Agressive' ? {"fontWeight":"bold","color":"#f1356d"}:{} }> 
                        {` ${animal.temperament}`}
                        </span> </p>
                    <p>Last Fed: {animal.lastFeedingTime}</p> 
                </div>
            ))}

        </div>    
     );
}
 
export default AnimalList;