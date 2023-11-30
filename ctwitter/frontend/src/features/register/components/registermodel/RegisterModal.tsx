import React from 'react'
import {Modal} from "../../../../components/Modal/Modal";
import './RegisterModal.css'
import RegistrationStepCounter from "../registration-step-counter/RegistrationStepCounter";

const RegisterModal = () => {
  return (

      <Modal>
          <div className="register-modal">
                <RegistrationStepCounter step={5}/>
          </div>
      </Modal>

  )
}

export default RegisterModal