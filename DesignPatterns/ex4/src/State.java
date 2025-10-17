public enum State {
    IDLE {
        @Override
        public void rendreMonnaie(MachineACafe machine) {
            throw new RuntimeException("Already Idle");
        }
    }, COLLECTE {
        @Override
        public void selectionnerBoisson(MachineACafe machine, ToucheBoisson toucheBoisson) {
            if (toucheBoisson.getPrix() > machine.montantEnCours) {
                machine.boisson = toucheBoisson;
                machine.afficherPasAssez(machine.boisson);
                machine.boisson = toucheBoisson;
                machine.setState(State.PAS_ASSEZ);
                return;
            }
            machine.montantEnCours -= toucheBoisson.getPrix();
            machine.afficherBoisson(toucheBoisson);
            machine.afficherMontant();
            if (machine.montantEnCours == 0)
                machine.setState(State.IDLE);
        }

    }, PAS_ASSEZ {
        @Override
        public void entrerMonnaie(MachineACafe machine, Piece piece) {
            machine.montantEnCours += piece.getValeur();
            machine.afficherMontant();
            if (machine.boisson.getPrix() > machine.montantEnCours) {
                machine.afficherPasAssez(machine.boisson);
            } else {
                machine.montantEnCours -= machine.boisson.getPrix();
                machine.afficherBoisson(machine.boisson);
                machine.boisson = null;
                machine.afficherMontant();
                if (machine.montantEnCours == 0)
                    machine.setState(State.IDLE);
                else
                    machine.setState(State.COLLECTE);
            }
        }

        @Override
        public void selectionnerBoisson(MachineACafe machine, ToucheBoisson toucheBoisson) {
            throw new IllegalStateException();
        }
    };

    public void entrerMonnaie(MachineACafe machine, Piece piece) {
        machine.montantEnCours += piece.getValeur();
        machine.afficherMontant();
        machine.setState(State.COLLECTE);
    }

    public void selectionnerBoisson(MachineACafe machine, ToucheBoisson toucheBoisson) {
        machine.setState(State.IDLE);
    }

    public void rendreMonnaie(MachineACafe machine) {
        machine.afficherRetour();
        machine.montantEnCours = 0;
        machine.boisson = null;
        machine.setState(State.IDLE);
    }
}
