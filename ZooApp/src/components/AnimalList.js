import {Link} from 'react-router-dom';

const AnimalList = ({animals,keeper,handleDelete}) => {
    

    return ( 
        <div className='animal-list'>
            <h2>Animals</h2>
            {animals.map((animal) => (
                <div className = 'animal-preview' key={animal.id}>
                    <Link to={`/animal/${animal.id}`}>
                        <h2>{animal.name}</h2>
                        <p>Enclolsure: {animal.enclosure}, Temperament:
                            <span style={animal.temperamentWarning === 1 ? {"fontWeight":"bold","color":"#f1356d"}:{} }> 
                            {` ${animal.temperament}`}
                            </span> </p>
                        <p>Last Fed: {animal.lastFeedingTime}</p> 
                    </Link>
                </div>
            ))}

        </div>    
     );
}
 
export default AnimalList;