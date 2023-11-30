import React from 'react'
import './Modal.css'

interface ModalProps {
    children: React.JSX.Element
}

export const Modal : React.FC<ModalProps> = (props : ModalProps) => {
  return (
    <div className="modal-overlay">
        <div className="modal-container bg-white">
            {props.children}
        </div>
       
    </div>
  )
}

