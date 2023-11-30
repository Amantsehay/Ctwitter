import React from 'react';
import {DisplayIcon} from "../../../../utils/registericon/displayIcon";
import './RegistrationStepCounter.css'

interface RegistrationStepCounterProps {
    step: number,
}
const RegistrationStepCounter: React.FC<RegistrationStepCounterProps> = ({step}) => {
    return (
        <div className="reg-step-counter-container">
            <div className="reg-step-counter-btn">
                {DisplayIcon(step)}
            </div>
            <div className="reg-step-counter-text">
                <p>Step {step} of 6</p>
            </div>
            
        </div>
    );
};

export default RegistrationStepCounter;