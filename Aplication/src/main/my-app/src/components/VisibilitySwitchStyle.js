import styled from 'styled-components';


export const SwitchRadio = styled.input`
  display: none;
`;

export const SwitchLabel = styled.label`
  position: relative;
  z-index: 2;
  float: left;
  width: 130px;
  line-height: 35px;
  color: rgba(0, 0, 0, 0.6);
  text-align: center;
  cursor: pointer;
  font-size: 18px;
  user-select: none;
  display:none;
  

  ${SwitchRadio}:checked + &{
    transition: 0.15s ease-out;
    color: #fff;
    display:block;
  }
  
`;

export const SwitchSelection = styled.span`
  display: block;
  position: absolute;
  z-index: 1;
  top: 0px;
  left: 0px;
  width: 955px;
  transform: translate(-50%);
  height: 30px;
  background: #636;
  border-radius: 3px;
  transition: left 0.25s ease-out, width .15s ease-out;
  
 
`;

export const Switch = styled.div`
  font-family: "Lucida Grande", Tahoma, Verdana, sans-serif;
  position: relative;
  height: 30px;
  width: 130px;
  background-color: #e4e4e4;
  border-radius: 3px;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.3), 0 1px rgba(255, 255, 255, 0.1);
  overflow:hidden;
  transition: width 0.2s ease-out;
  transition-delay: .2s;
  
  
  &:hover{
    width: 390px;
    transition-delay: 0s;
  }
  
  &:hover .switch-selection{
      width: 130px;
      transform: none;
      transition: left 0.25s ease-out;
      
  }
  
  &:hover ${SwitchLabel}{
    display: block;
  }
 
`;




