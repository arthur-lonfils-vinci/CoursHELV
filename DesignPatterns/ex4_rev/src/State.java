public enum State {
    IDLE {
        @Override
        public void rendreMonnaie(MachineACafe machineACafe) {
            throw new RuntimeException("Already Idle");
        }
    },
    COLLECT {
        @Override
        public void selectionnerBoisson(MachineACafe machineACafe, ToucheBoisson toucheBoisson) {
            if (toucheBoisson.getPrix() > machineACafe.montantEnCours) {
                machineACafe.boisson = toucheBoisson;
                machineACafe.afficherPasAssez(machineACafe.boisson);
                machineACafe.boisson = toucheBoisson;
                machineACafe.setState(State.PAS_ASSEZ);
                return;
            }
            machineACafe.montantEnCours -= toucheBoisson.getPrix();
            machineACafe.afficherBoisson(toucheBoisson);
            machineACafe.afficherMontant();
            if (machineACafe.montantEnCours == 0) {
                machineACafe.setState(State.IDLE);
            }
        }
    },
    PAS_ASSEZ {
        @Override
        public void entrerMonnaie(MachineACafe machineACafe, Piece piece) {
            machineACafe.montantEnCours += piece.getValeur();
            machineACafe.afficherMontant();
            if (machineACafe.boisson.getPrix() > machineACafe.montantEnCours) {
                machineACafe.afficherPasAssez(machineACafe.boisson);
            } else {
                machineACafe.montantEnCours -= machineACafe.boisson.getPrix();
                machineACafe.afficherBoisson(machineACafe.boisson);
                machineACafe.boisson = null;
                machineACafe.afficherMontant();
                if (machineACafe.montantEnCours == 0)
                    machineACafe.setState(State.IDLE);
                else
                    machineACafe.setState(State.COLLECT);
            }
        }

        @Override
        public void selectionnerBoisson(MachineACafe machineACafe, ToucheBoisson toucheBoisson) {
            throw new IllegalStateException();
        }
    };

    public void entrerMonnaie(MachineACafe machineACafe, Piece piece) {
        machineACafe.montantEnCours += piece.getValeur();
        machineACafe.afficherMontant();
        machineACafe.setState(State.COLLECT);
    }

    public void selectionnerBoisson(MachineACafe machineACafe, ToucheBoisson toucheBoisson) {
        machineACafe.setState(State.IDLE);
    }

    public void rendreMonnaie(MachineACafe machineACafe) {
        machineACafe.afficherRetour();
        machineACafe.montantEnCours = 0;
        machineACafe.boisson = null;
        machineACafe.setState(State.IDLE);
    }
}
