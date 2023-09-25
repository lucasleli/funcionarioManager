package br.com.lucasleli.funcionarios;

import br.com.lucasleli.funcionarios.web.dto.request.FuncionarioCreateRequestDTO;
import br.com.lucasleli.funcionarios.web.dto.request.FuncionarioUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static FuncionarioCreateRequestDTO getFuncionarioCreateRequestDTO() {
        FuncionarioCreateRequestDTO createDto = new FuncionarioCreateRequestDTO();
        createDto.setNome("John Doe");
        createDto.setDesignacao("Developer");
        createDto.setSalario(5000.0);
        createDto.setTelefone("123-456-7890");
        createDto.setEndereco("123 Main St");
        return createDto;
    }

    private static FuncionarioUpdateRequestDto getFuncionarioUpdateRequestDto() {
        FuncionarioUpdateRequestDto createDto = new FuncionarioUpdateRequestDto();
        createDto.setDesignacao("Engineer");
        createDto.setSalario(5000.0);
        createDto.setTelefone("123-456-7890");
        createDto.setEndereco("123 Main St");
        return createDto;
    }

    @Test()
    public void testCreateAndVerifyFlow() throws Exception {
        FuncionarioCreateRequestDTO createDto = getFuncionarioCreateRequestDTO();

        mockMvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testGetFuncionarioById() throws Exception {
        FuncionarioCreateRequestDTO createDto = getFuncionarioCreateRequestDTO();

        mockMvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());

        FuncionarioUpdateRequestDto updateDto = getFuncionarioUpdateRequestDto();

        mockMvc.perform(put("/api/funcionarios/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.designacao").value(updateDto.getDesignacao()));
    }

    @Test
    public void testCreateDeleteAndGetAll() throws Exception {
        // 1. Cria o primeiro funcionário
        FuncionarioCreateRequestDTO createDto1 = getFuncionarioCreateRequestDTO();
        mockMvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        // 2. Cria o segundo funcionário
        FuncionarioCreateRequestDTO createDto2 = getFuncionarioCreateRequestDTO();
        createDto2.setNome("Jane Doe");  // Alterando o nome para diferenciar
        mockMvc.perform(post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto2)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2));

        // 3. Deleta o primeiro funcionário
        mockMvc.perform(delete("/api/funcionarios/1"))
                .andExpect(status().isOk());

        // 4. Consulta todos os funcionários e espera encontrar apenas o segundo
        mockMvc.perform(get("/api/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))  // Espera encontrar apenas um funcionário
                .andExpect(jsonPath("$[0].id").value(2));  // Espera que o ID do único funcionário retornado seja 2
    }

}
