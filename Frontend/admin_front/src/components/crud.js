import axios from 'axios'

const getHeader = () => {
    return {
        "headers": {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": "Bearer " + localStorage.getItem('token'),
        }
      }
}

const get = (url, func) => {
    axios.get(url, getHeader())
            .then(results => func(results.data, false, null))
            .catch(error => func(null, false, error));
}

const post = (url, data, func) => {
    axios.post(url, JSON.stringify(data), getHeader())
            .then(results => func(results.data))
            .catch(error => alert(error))
}

const update = (url, data, func) => {
    console.log(JSON.stringify(data));
    
    axios.put(url + "/" + data.id, JSON.stringify(data), getHeader())
            .then(results => func(results.data))
            .catch(error => alert(error))
}

const del = (url, id, func) => {
    axios.delete(url + "/" + id, JSON.stringify(id), getHeader())
            .then(func(id))
            .catch(error => alert(error))
}
export {get, post, update, del};