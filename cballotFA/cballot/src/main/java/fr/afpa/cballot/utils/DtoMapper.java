package fr.afpa.cballot.utils;

import fr.afpa.cballot.dto.*;
import fr.afpa.cballot.entity.*;

public class DtoMapper {

    /* ------------------------- Formation ------------------------- */
    public static FormationDTO toFormationDto(Formation formation) {
        if (formation == null)
            return null;
        return new FormationDTO(
                formation.getId(),
                formation.getNom(), null);
    }

    public static Formation toEntity(FormationDTO dto, Organisateur organisateur) {
        if (dto == null)
            return null;
        Formation f = new Formation();
        f.setId(dto.getId());
        f.setNom(dto.getNom());
        f.setOrganisateur(organisateur);
        return f;
    }

    /* ------------------------- SessionFormation ------------------------- */
    public static SessionFormationDTO toDto(SessionFormation s) {
        return new SessionFormationDTO(
                s.getId(),
                s.getDateDebut(),
                s.getDateFin(),
                s.getFormation() != null ? s.getFormation().getId() : null);
    }

    public static SessionFormation toEntity(SessionFormationDTO dto, Formation formation) {
        SessionFormation s = new SessionFormation();
        s.setId(dto.getId());
        s.setDateDebut(dto.getDateDebut());
        s.setDateFin(dto.getDateFin());
        s.setFormation(formation);
        return s;
    }

    /* ------------------------- Stagiaire ------------------------- */
    public static StagiaireDTO toDto(Stagiaire st) {
        return new StagiaireDTO(
                st.getId(),
                st.getEmail(),
                st.getSessionFormation() != null ? st.getSessionFormation().getId() : null);
    }

    public static Stagiaire toEntity(StagiaireDTO dto, SessionFormation SessionFormation) {
        Stagiaire st = new Stagiaire();
        st.setId(dto.getId());
        st.setEmail(dto.getEmail());
        st.setSessionFormation(SessionFormation);
        return st;
    }

    /* ------------------------- Organisateur ------------------------- */
    public static OrganisateurDTO toDto(Organisateur o) {
        return new OrganisateurDTO(o.getId(), o.getNom(), o.getEmail());
    }

    public static Organisateur toEntity(OrganisateurDTO dto) {
        Organisateur o = new Organisateur();
        o.setId(dto.getId());
        o.setNom(dto.getNom());
        o.setEmail(dto.getEmail());
        return o;
    }

    /* ------------------------- RoleOrganisateur ------------------------- */
    public static RoleOrganisateurDTO toDto(RoleOrganisateur r) {
        return new RoleOrganisateurDTO(r.getId(), r.getLibelle());
    }

    public static RoleOrganisateur toEntity(RoleOrganisateurDTO dto) {
        RoleOrganisateur r = new RoleOrganisateur();
        r.setId(dto.getId());
        r.setLibelle(dto.getLibelle());
        return r;
    }

    /* ------------------------- Scrutin ------------------------- */
    public static ScrutinDTO toDto(Scrutin s) {
        return new ScrutinDTO(
                s.getId(),
                s.getDateScrutin(),
                s.getSessionFormation() != null ? s.getSessionFormation().getId() : null);
    }

    public static Scrutin toEntity(ScrutinDTO dto, SessionFormation sessionFormation) {
        Scrutin s = new Scrutin();
        s.setId(dto.getId());
        s.setDateScrutin(dto.getDateScrutin());
        s.setSessionFormation(sessionFormation);
        return s;
    }

    /* ------------------------- Tour ------------------------- */
    public static TourDTO toDto(Tour t) {
        return new TourDTO(
                t.getId(),
                t.getNumero(),
                t.getScrutin() != null ? t.getScrutin().getId() : null);
    }

    public static Tour toEntity(TourDTO dto, Scrutin scrutin) {
        Tour t = new Tour();
        t.setId(dto.getId());
        t.setNumero(dto.getNumero());
        t.setScrutin(scrutin);
        return t;
    }

    /* ------------------------- Binome ------------------------- */
    public static BinomeDTO toDto(Binome b) {
        return new BinomeDTO(
                b.getId(),
                b.getPrincipal() != null ? b.getPrincipal().getId() : null,
                b.getSuppleant() != null ? b.getSuppleant().getId() : null,
                b.getTour() != null ? b.getTour().getId() : null);
    }

    public static Binome toEntity(BinomeDTO dto, Stagiaire principal, Stagiaire suppleant, Tour tour) {
        Binome b = new Binome();
        b.setId(dto.getId());
        b.setPrincipal(principal);
        b.setSuppleant(suppleant);
        b.setTour(tour);
        return b;
    }

    /* ------------------------- Vote ------------------------- */
    public static VoteDTO toDto(Vote v) {
        return new VoteDTO(
                v.getId(),
                v.getUuid(),
                v.getBinome() != null ? v.getBinome().getId() : null,
                v.getTour() != null ? v.getTour().getId() : null,
                v.getStagiaire() != null ? v.getStagiaire().getId() : null);
    }

    public static Vote toEntity(VoteDTO dto, Binome binome, Tour tour, Stagiaire stagiaire) {
        Vote v = new Vote();
        v.setId(dto.getId());
        v.setUuid(dto.getUuid());
        v.setBinome(binome);
        v.setTour(tour);
        v.setStagiaire(stagiaire);
        return v;
    }

    /* ------------------------- VotingToken ------------------------- */
    public static VotingTokenDTO toDto(VotingToken v) {
        return new VotingTokenDTO(
                v.getId(),
                v.getToken(),
                v.getScrutin() != null ? v.getScrutin().getId() : null);
    }

    public static VotingToken toEntity(VotingTokenDTO dto, Scrutin scrutin, String token) {
        VotingToken v = new VotingToken();
        v.setId(dto.getId());
        v.setToken(token); // ici on force l’UUID généré
        v.setScrutin(scrutin);
        return v;
    }

}
