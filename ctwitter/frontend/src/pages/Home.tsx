import React from 'react'; // this will import react from the react library

import './Home.css'; // this will import the css filr for the home page
import '../assets/global.css'; // this will import the global css file for the app
import { Modal } from '../components/Modal/Modal';
import RegisterModal from '../features/register/components/registermodel/RegisterModal';
export const Home:React.FC = () => {
    return (
        <div className="home-container bg-color"> 
            <RegisterModal/>
            
        </div>
    )
}




