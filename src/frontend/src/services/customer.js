import axios from 'axios'

const apiUri = '/api/customer'

const CustomerService = {
    addCustomer(customerData){
        return axios.post(`${apiUri}/add`, customerData)
    }
}

export default CustomerService