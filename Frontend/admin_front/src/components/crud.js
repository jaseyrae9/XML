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
            .catch(error => {
                const msg = error + " ---> " + error.response.data.message
                func(null, false, msg)})
}

const post = (url, data, func) => {
    axios.post(url, JSON.stringify(data), getHeader())
            .then(results => func(results.data))
            .catch(error => alert(error + "\n\n" + error.response.data.message))
}

const update = (url, data, func) => {
    axios.put(url + "/" + data.id, JSON.stringify(data), getHeader())
            .then(results => func(results.data))
            .catch(error => alert(error + "\n\n" + error.response.data.message))
}

const del = (url, id, func) => {
    console.log(url + "/" + id);
    console.log(JSON.stringify(id));
    console.log(getHeader());
    
    axios.delete(url + "/" + id, getHeader())
            .then(func(id))
            .catch(error => alert(error + "\n\n" + error.response.data.message))
}
export {get, post, update, del, getHeader};