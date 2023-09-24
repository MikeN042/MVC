import { useState, useEffect } from "react"

const useFetch = (url) => {

    const [data,setData] = useState(null)
    const [isLoading,setIsLoading] = useState(true)
    const [error,setError] = useState(null)

    useEffect(()=>{
        fetch(url)
            .then(res =>{
                if(!res.ok){
                    throw Error('something went wrong during loading')
                }
                return res.json()
            })
            .then(data=>{
                console.log(data)
                setIsLoading(false)
                setError(null)
                setData(data)
            })
            .catch(err=>{
                setError(err.message)
                setIsLoading(false)
            })
    },[url])


    return{data,isLoading,error}
}



export default useFetch;